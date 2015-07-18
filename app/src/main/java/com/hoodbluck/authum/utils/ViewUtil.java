package com.hoodbluck.authum.utils;

import android.content.Context;
import android.widget.EditText;

import com.hoodbluck.authum.R;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
public class ViewUtil {

    public static boolean isFormValid(Context context, ArrayList<EditText> editTexts) {

        Iterator<EditText> iterator = editTexts.iterator();
        while (iterator.hasNext()) {
            if (StringUtils.isNotBlank(iterator.next().getText().toString())) {
                iterator.remove();
            }
        }

        if (editTexts.size() > 0) {
            warnUser(context, editTexts);
            return false;
        }

        return true;
    }

    private static void warnUser(Context context, ArrayList<EditText> editTexts) {
        for (EditText editText : editTexts) {
            editText.setHint(context.getString(R.string.required_field));
            editText.setHintTextColor(context.getResources().getColor(R.color.red));
        }
    }
}
