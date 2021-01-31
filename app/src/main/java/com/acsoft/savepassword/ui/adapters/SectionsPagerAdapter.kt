package com.acsoft.savepassword.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.acsoft.savepassword.R
import com.acsoft.savepassword.ui.accounts.FavoritesFragment
import com.acsoft.savepassword.ui.accounts.PasswordsFragment

private val TAB_TITLES = arrayOf(
        R.string.accounts,
        R.string.favorites
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragment: Fragment? = null

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        when(position) {
            0 -> fragment = PasswordsFragment()
            1 -> fragment = FavoritesFragment()
        }
        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}