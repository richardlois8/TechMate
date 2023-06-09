package id.co.ukdw.techmate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import id.co.ukdw.techmate.databinding.ActivityMainBinding
import id.co.ukdw.techmate.engine.CBREngine

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var engine: CBREngine
    private val navController by lazy { findNavController(R.id.fragmentContainerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEngine()
        setFirstInit()
        setListenerBottomNav()
    }

    private fun setFirstInit() {
        val prefs = getSharedPreferences("tech_mate_konfig", MODE_PRIVATE)
        if (prefs.getBoolean("first_init", true)) {
            getEngine().insertCases()
            val editor = prefs.edit()
            editor.putBoolean("first_init", false)
            editor.apply()
        }
        getEngine().initCases()
    }

    private fun initEngine() {
        engine = CBREngine(this)
    }

    fun getEngine(): CBREngine {
        return engine
    }

    private fun setListenerBottomNav() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    navController.navigate(R.id.action_global_homeFragment)
                    true
                }

                R.id.menu_search -> {
                    navController.navigate(R.id.action_global_searchFragment)
                    true
                }

                R.id.menu_insert -> {
                    navController.navigate(R.id.action_global_insertFragment)
                    true
                }

                R.id.menu_about -> {
                    navController.navigate(R.id.action_global_aboutFragment)
                    true
                }

                else -> false
            }
        }
    }
}