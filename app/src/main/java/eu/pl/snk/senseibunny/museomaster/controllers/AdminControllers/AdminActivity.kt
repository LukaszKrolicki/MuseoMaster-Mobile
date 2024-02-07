package eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityAdminBinding


class AdminActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private  lateinit var fragmentManage: FragmentManager
    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val toogle = ActionBarDrawerToggle(this,binding.drawerLayout, binding.toolbar,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        fragmentManage = supportFragmentManager

        binding.navigationDrawer.setNavigationItemSelectedListener(this)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_add_user -> {openFragment(AddUserFragment())}
            R.id.nav_add_room-> {openFragment(AddRoomFragment())}
            R.id.nav_user_list->{openFragment(UserListFragment())}
            R.id.nav_bugs->{openFragment(ReportFragment())}
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.getOnBackPressedDispatcher().onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManage.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}