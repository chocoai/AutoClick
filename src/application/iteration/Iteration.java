package application.iteration;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;


@SuppressWarnings({"rawtypes", "deprecation"})
public class Iteration extends ActivityInstrumentationTestCase2 {

    /**
     * 被测应用包名
     */
    private static final String PACKAGE = "被测应用包名";

    /**
     * 被测应用Activity入口
     */
    private static final String LAUNCHER_ACTIVITY = "被测应用Activity入口";

    private static Class<?> launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public Iteration() {
        super(PACKAGE, launcherActivityClass);
    }

    private Solo solo;

    @Before
    public void setUp() throws Exception {
        Solo.Config config = new Solo.Config();
        // 遍历模式
        config.mode = Solo.Config.Mode.REPTILE;
        config.homeActivity = "被测应用主页Activity";
        config.loginActivity = "被测应用登录Activity";
        config.loginAccount = "登录帐号";
        config.loginPassword = "登录密码";
        config.loginId = "登录按钮ID";
        // 被测应用包名
        config.PACKAGE = PACKAGE;
        config.ignoreActivities = new String[]{"忽略的Activity，此数组中的Activity将不会被遍历"};
        config.ignoreViews = new String[]{"忽略的View，此数组中的View将不会被点击，需填入完整的资源ID"};

        solo = new Solo(getInstrumentation(), config, getActivity());
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }


    /**
     * 自动遍历入口
     * @throws Exception 抛出异常
     */
    public void test_iteration() throws Exception {
        solo.startIteration();
    }

}