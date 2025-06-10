package models.lombok.reqresin;

import io.qameta.allure.Step;
import lombok.Data;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
public class UpdateResponseModel {
    private String name, job, updatedAt;

    @Step("Проверка поля name")
    public void checkName(String expectedName){
        assertEquals(expectedName, name, "Поле name не совпадает");
    }

    @Step("Проверка поля job")
    public void checkJob(String expectedJob){
        assertEquals(expectedJob, job, "Поле job не совпадает");
    }
}
