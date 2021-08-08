package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper

import android.app.Dialog
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedUtilsHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedUtilsHelper.Companion.handleLanguage

open class ParentFragment : Fragment(), setupView {

    companion object{
        val UNAUTHORIZED_CODE = 401
    }

    private var v_noInternet: View? = null
    private var v_loading: View? = null
    private var progress_bar: ProgressBar? = null
    private var vEmpty: View? = null
    private var v_serverError: View? = null
    private var tvError: TextView? = null
    private var btn_retry: Button? = null

    private var token: String=""
    private var language: String = ""
    private lateinit var mPaginate: Paginate

    private var loadingDialog: Dialog? = null
    private var loginDialog: Dialog? = null

    private var retryClickListener: setupView? = null
    private var navController: NavController? = null


    override fun getIntentData() {

    }

    override fun setupSettings() {
//        val userData: UserData?= SharedPrefManager.getUserData(context!!)
//
//        token=if (userData!=null){
//            "Bearer "+userData?.token
//        }
//        else{
//            ""
//        }
        handleLanguage(requireContext())
//        mPaginate = Paginate(0, 1, 1, "", "", 1, 1);
    }

    override fun setupComponents(view: View?) {
        view?.let {try {
            navController = Navigation.findNavController(it)
        }catch (e: java.lang.Exception){}
            v_loading = view.findViewById(R.id.v_loading)
            vEmpty = view.findViewById(R.id.v_empty)

            v_serverError = view.findViewById(R.id.v_serverError)
            v_noInternet = view.findViewById(R.id.v_noInternet)
            tvError = view.findViewById(R.id.tvError)
            btn_retry = view.findViewById(R.id.btn_retry)

            if (v_noInternet != null && btn_retry != null) {
                btn_retry?.setOnClickListener { retryClickListener?.onRetryClick() }
            }

            setRetryClickListener(this)
           /* progress_bar = view.findViewById(R.id.progress_bar)
            */
        }
    }

    override fun handleToolbar() {

    }

    override fun onBackPress() {

    }

    override fun onComponentsClick() {

    }

    override fun onRetryClick() {
        v_noInternet?.visibility=View.GONE

    }

    override fun showMainLoading() {
        v_loading?.visibility = View.VISIBLE
    }

    override fun hideMainLoading() {
        v_loading?.visibility = View.GONE
    }

    override fun showLoadMoreProgress() {
        progress_bar?.visibility = View.VISIBLE
    }

    override fun hideLoadMoreProgress() {
        progress_bar?.visibility = View.GONE
    }

    override fun showSubLoading() {
        if (loadingDialog == null) {
            loadingDialog = Dialog(requireActivity(), R.style.NewDialog1)
            loadingDialog!!.setContentView(R.layout.view_dialog_loading)
            loadingDialog!!.setCancelable(false)
        }
        loadingDialog!!.show()
    }

    override fun hideSubLoading() {
        loadingDialog?.dismiss()
    }

    override fun handleErrorMsg(error: GenericError?) {
        hideMainLoading()
        hideLoadMoreProgress()
        hideSubLoading()


        //onFailure Handle
        error?.let {
            if (error.throwable != null) {
                if (error.isToast) {
                    Toast.makeText(context, R.string.connection_problem, Toast.LENGTH_SHORT).show()
                } else {
                    v_noInternet!!.visibility = View.VISIBLE
                }
            } else {
                if (error.responseCode == null) {

                    // onResponse Error
                    if (error.isToast) {
                        Toast.makeText(activity, error.msg, Toast.LENGTH_SHORT).show()
                    } else {
                        //TODO replace with general view later
                        v_serverError!!.visibility = View.VISIBLE
                        if (error.msg?.length !== 0) {
                            tvError?.setText(error.msg)
                        }
                    }
                } else {
                    if (error.responseCode!!.equals(ParentFragment.UNAUTHORIZED_CODE)) {
                        showLoginDialog(error.msg)
                    } else {
                        // onResponse Error
                        if (error.isToast) {
                            Toast.makeText(activity, error.msg, Toast.LENGTH_SHORT).show()
                        } else {
                            v_serverError!!.visibility = View.VISIBLE
                            if (error.msg?.length !== 0) {
                                v_serverError!!.visibility = View.GONE
                                //tvError?.setText(error.msg)
                            }
                        }
                    }
                }
            }
        }

    }

    // general methods -->

    fun resetPage() {
        mPaginate?.current_page = 1
        mPaginate?.total = 0
        mPaginate?.total_pages = 0
    }

    open fun getToken(): String {
        return token
    }

    open fun getLanguage(): String {
        return language
    }

    open fun getmPaginate(): Paginate {
        return mPaginate?:Paginate()
    }

    open fun getNavController(): NavController? {
        return navController
    }

    open fun showLoginDialog(loginMsg: String?) {

    }

    open fun setRetryClickListener(retryClickListener: setupView) {
        this.retryClickListener = retryClickListener
    }

    open fun <T> isEmptyList(list: List<T>?): Boolean {
        return if (list != null && list.size > 0) {
            vEmpty!!.visibility = View.GONE
            false
        } else {
            vEmpty!!.visibility = View.VISIBLE
            true
        }
    }

}