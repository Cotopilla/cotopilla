package tests.parsingfiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFilesParsingTest {

    private String zipFile = "parsingfiles/readcheckfiles.zip";
    private String emptyZipFile = "parsingfiles/empty.zip";
    private ClassLoader cl = ZipFilesParsingTest.class.getClassLoader();

    @Test
    void pdfFromZipParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream(emptyZipFile)) {
            try (ZipInputStream zis = new ZipInputStream(is)) {
                if (zis.getNextEntry() != null) {
                    ZipEntry zipEntry;
                    while ((zipEntry = zis.getNextEntry()) != null) {
                        if (zipEntry.getName().endsWith(".pdf")) {
                            PDF pdf = new PDF(zis);
                            Assertions.assertTrue(pdf.text.contains("Тестовый PDF-документ"));
                        }
                    }
                } else throw new FileNotFoundException("Архив пустой");
            }
        }
    }

    @Test
    void xlsFromZipParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream(zipFile)) {
            try (ZipInputStream zis = new ZipInputStream(is)) {
                if (zis.getNextEntry() != null) {
                    ZipEntry zipEntry;
                    while ((zipEntry = zis.getNextEntry()) != null) {
                        if (zipEntry.getName().endsWith(".xlsx")) {
                            XLS xls = new XLS(zis);
                            String actualValue = xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue();
                            Assertions.assertEquals("Тестовый XLS-документ", actualValue);
                        }
                    }
                } else throw new FileNotFoundException("Архив пустой");
            }
        }
    }

    @Test
    void csvFromZipParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream(zipFile)
        )) {
            if (zis.getNextEntry() != null) {
                ZipEntry zipEntry;
                while ((zipEntry = zis.getNextEntry()) != null) {
                    if (zipEntry.getName().endsWith(".csv")) {
                        CSVReader csv = new CSVReader(new InputStreamReader(zis));
                        List<String[]> csvStrings = csv.readAll();
                        Assertions.assertEquals(5, csvStrings.size());
                        Assertions.assertArrayEquals(
                                new String[]{"Тестовый CSV-документ"},
                                csvStrings.get(4)
                        );
                        Assertions.assertArrayEquals(
                                new String[]{"Это документ в формате PDF", " который был создан для тестирования загрузки файлов."},
                                csvStrings.get(2)
                        );
                    }
                }
            } else throw new FileNotFoundException("Архив пустой");
        }
    }
}
