import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.service.query.NodeQuery;

import static java.awt.GraphicsEnvironment.isHeadless;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.matcher.control.ComboBoxMatchers.hasItems;
import static org.testfx.matcher.control.ComboBoxMatchers.hasSelectedItem;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

class MainTest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        new Main().start(stage);
    }
    @Test
    public void test4differentScenarios () {

        clickOn("#nameTF");
        write("Kevin");
        clickOn("#passwordTF");
        write("11223");
        clickOn("#loginButton");
        clickOn("#ordersMenu");
        clickOn("#newBook");
        clickOn("#titleTF");
        write("Titelo la testelo");
        clickOn("#isbnTF");
        write("1234testelo1234");
        clickOn("#quantityTF");
        write("100");
        clickOn("#SpriceTF");
        write("1399");
        clickOn("#BpriceTF");
        write("499");
        clickOn("#rbPaperback");
        clickOn("#descriptionTA");
        write("Titelo la testelo is the best book your money can buy, OR IS IT???");
        clickOn("#cbo");
        clickOn("Fyodor Dostoevsky");
        clickOn("#HISTORICAL");
        clickOn("#submitBtn");
        clickOn("#booksMenu");
        clickOn("#sellBooks");
        clickOn("#bookComboBox");
        clickOn("Titelo la testelo by Fyodor Dostoevsky");
        clickOn("#quantityTF");
        write("10");
        clickOn("#createBillBtn");
        clickOn("#addBookBtn");
        clickOn("#submitBtn");
        clickOn("#profileMenu");
        clickOn("#usersMenu");
        clickOn("#registerUser");
        clickOn("#fNameTF");
        write("TestUserName");
        clickOn("#lNameTF");
        write("TestLastName");
        clickOn("#day");
        write("30");
        clickOn("#month");
        write("3");
        clickOn("#year");
        write("2000");
        clickOn("#emailTF");
        write("testuseremail@test.com");
        clickOn("#phoneTF");
        write("+355676443210");
        clickOn("#salaryTF");
        write("4000");
        clickOn("#roleTF");
        write("Librarian");
        clickOn("#passwordTF");
        write("TEST444");
        clickOn("#createButton");
        clickOn("#profileMenu");
        clickOn("#usersMenu");
        clickOn("#modifyUser");
        clickOn("#userComboBox");
        clickOn("TestUserName TestLastName");
        clickOn("#deleteButton");
        verifyThat("#deleteButton", NodeMatchers.isDisabled());
        sleep(2000);
    }



}