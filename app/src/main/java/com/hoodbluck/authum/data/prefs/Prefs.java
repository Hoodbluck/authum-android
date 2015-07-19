package com.hoodbluck.authum.data.prefs;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface Prefs {
    String patterSha();
    String email();
}
