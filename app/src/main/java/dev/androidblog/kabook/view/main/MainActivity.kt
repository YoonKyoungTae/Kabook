package dev.androidblog.kabook.view.main

import android.widget.Toast
import dev.androidblog.kabook.R
import dev.androidblog.kabook.api.dao.BookDAO
import dev.androidblog.kabook.view.base.BaseActivity
import dev.androidblog.kabook.view.detail.BookDetailFragment
import dev.androidblog.kabook.view.list.BookListFragment

class MainActivity : BaseActivity(R.layout.activity_main), MainContract.View {

    private val listFragment = BookListFragment.newInstance()
    private var pressTime: Long = 0L

    override fun initView() {
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_parent, listFragment)
            .commit()

        listFragment.onClickBookItem = object : BookListFragment.OnClickBookItem {
            override fun onClick(book: BookDAO.Documents) {
                replaceFragment(book)
            }
        }
    }

    fun replaceFragment(book: BookDAO.Documents) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(
                R.anim.fragment_anim_right_in,
                R.anim.fragment_anim_left_in,
                R.anim.fragment_anim_left_out,
                R.anim.fragment_anim_right_out
            )
            .add(R.id.fl_parent, BookDetailFragment.newInstance(book))
            .commit()
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - pressTime >= 2000) {
            pressTime = System.currentTimeMillis()
            Toast.makeText(applicationContext, "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - pressTime < 2000) {
            super.onBackPressed()
        }
    }
}
