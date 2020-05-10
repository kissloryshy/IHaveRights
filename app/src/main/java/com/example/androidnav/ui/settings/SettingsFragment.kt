package com.example.androidnav.ui.settings

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.androidnav.R
import com.example.androidnav.data.UserInfo
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    private lateinit var slideshowViewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var userInfo: UserInfo = UserInfo(context, "UserInfo", null, 1)
        etName.setText(userInfo.getName())

        btnChangeName.setOnClickListener {
            var userInfo: UserInfo = UserInfo(context, "UserInfo", null, 1)
            userInfo.saveName(etName.text.toString())
            var toast: Toast = Toast.makeText(context, "changed", Toast.LENGTH_LONG)
            toast.show()
        }

        btnCleardb.setOnClickListener {
            var toast: Toast = Toast.makeText(context, "Сохраненные статьи удалены", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
