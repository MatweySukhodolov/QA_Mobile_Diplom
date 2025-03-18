package ru.iteco.fmhandroid.ui.Data;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;


import ru.iteco.fmhandroid.R;

public class Main_About_ControlPanelHelper {
    // Метод для клика на элемент RecyclerView по позиции
    public static void clickRecyclerViewItemLoveIssAll(int position) {
        onView(ViewMatchers.withId(R.id.our_mission_item_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, ViewActions.click()));
    }

    // Control panel
    // Метод для клика на элемент в RecyclerView по позиции
    public static void clickRecyclerViewItemControlPanel(int recyclerViewId, int position) {
        onView(ViewMatchers.withId(recyclerViewId))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, ViewActions.click()));
    }
}


