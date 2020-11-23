package francisco.calado.wtest.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentNavigator(
    private val fragmentManager: FragmentManager,
    private var containerId: Int
) {

    fun navigateToInitialFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, fragment::class.java.simpleName)
            .commit()
    }

    fun navigateToFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, fragment::class.java.simpleName)
            .addToBackStack(fragment::class.java.simpleName).commit()
    }
}