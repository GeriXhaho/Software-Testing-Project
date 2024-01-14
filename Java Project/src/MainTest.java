import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.service.query.NodeQuery;

import static java.awt.GraphicsEnvironment.isHeadless;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

class MainTest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        new Main().start(stage);
    }
    @Test
    public void testEnglishInput () {

        clickOn("#nameTF");
        write("This is a test!");
        verifyThat("#nameTF", TextInputControlMatchers.hasText("This is a test!"));

    }



}