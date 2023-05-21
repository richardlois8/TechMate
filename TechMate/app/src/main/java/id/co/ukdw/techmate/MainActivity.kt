package id.co.ukdw.techmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.co.ukdw.techmate.databinding.ActivityMainBinding
import id.co.ukdw.techmate.engine.CBREngine

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var engine : CBREngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEngine()
    }

    private fun initEngine() {
        engine = CBREngine(this)
    }

    fun getEngine(): CBREngine {
        return engine
    }
}