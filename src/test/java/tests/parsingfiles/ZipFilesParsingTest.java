package tests.parsingfiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFilesParsingTest {

    private String zipFile = "parsingfiles/readcheckfiles.zip";
    private String emptyZipFile = "parsingfiles/empty.zip";
    private ClassLoader cl = ZipFilesParsingTest.class.getClassLoader();

    @Test
    void pdfFromZipParsingTest() throws Exception {
        boolean pdfFound = false;
        try (InputStream is = cl.getResourceAsStream(emptyZipFile)) {
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".pdf")) {
                        pdfFound = true;
                        PDF pdf = new PDF(zis);
                        Assertions.assertTrue(pdf.text.contains("Тестовый PDF-документ"));
                    }
                }
                assertThat(pdfFound).isTrue();
            }
        }
    }

    @Test
    void xlsFromZipParsingTest() throws Exception {
        boolean xlsFound = false;
        try (InputStream is = cl.getResourceAsStream(emptyZipFile)) {
            try (ZipInputStream zis = new ZipInputStream(is)) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".xlsx")) {
                        xlsFound = true;
                        XLS xls = new XLS(zis);
                        String actualValue = xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
                        Assertions.assertEquals("Тестовый XLS-документ", actualValue);
                    }
                }
                assertThat(xlsFound).isTrue();
            }
        }
    }

    @Test
    void csvFromZipParsingTest() throws Exception {
        boolean csvFound = false;
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream(emptyZipFile)
        )) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".csv")) {
                        csvFound = true;
                        CSVReader csv = new CSVReader(new InputStreamReader(zis));
                        List<String[]> csvStrings = csv.readAll();
                        Assertions.assertEquals(5, csvStrings.size());
                        Assertions.assertArrayEquals(
                                new String[]{"Тестовый CSV-документ"},
                                csvStrings.get(4)
                        );
                        Assertions.assertArrayEquals(
                                new String[]{"Это документ в формате CSV", " который был создан для тестирования загрузки файлов."},
                                csvStrings.get(2)
                        );
                    }
                }
                assertThat(csvFound).isTrue();
        }
    }
}