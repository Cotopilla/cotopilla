package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ModalDialogComponent {

    private SelenideElement modalDialog = $(".modal-dialog"),
            modalTitle = $("#example-modal-sizes-title-lg");

    private String modalText = "Thanks for submitting the form";

    public ModalDialogComponent waitingForm() {
        modalDialog.should(appear);
        modalTitle.shouldHave(text(modalText));

        return this;
    }

    public ModalDialogComponent notAppearingForm() {
        modalDialog.shouldNot(appear);

        return this;
    }
}
