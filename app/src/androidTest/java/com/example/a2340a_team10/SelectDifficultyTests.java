package com.example.a2340a_team10;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.a2340a_team10.R;
import com.example.a2340a_team10.view.InitialConfiguration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class SelectDifficultyTests {

    @Before
    public void setUp() {
        ActivityScenario.launch(InitialConfiguration.class);
    }

    @Test
    public void testNoSelectionBeforeStart() {
        // Click the start button without making a selection

        onView(withId(R.id.inputName)).perform(typeText("TestHero"), closeSoftKeyboard());

        // Click the submit button after setting the name
        onView(withId(R.id.buttonSubmit)).perform(click());

        // Select a character choice (e.g., female elf)
        onView(withId(R.id.char_f_elf)).perform(click());
        onView(withId(R.id.startButton)).perform(click());
        // Check if the textViewResult displays the appropriate error message
        onView(withId(R.id.greeting)).check(matches(withText("Please choose difficulty.")));
    }
}
