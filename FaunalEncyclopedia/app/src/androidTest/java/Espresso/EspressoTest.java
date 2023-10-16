package Espresso;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.g12.faunalencyclopedia.LoginActivity;
import com.g12.faunalencyclopedia.R;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {
    @Before
    public void setUp() {
        ActivityScenario.launch(LoginActivity.class);
    }

    @Test
    public void contentdisplayedundereachother(){
        Espresso.onView(ViewMatchers.withId(R.id.Title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
