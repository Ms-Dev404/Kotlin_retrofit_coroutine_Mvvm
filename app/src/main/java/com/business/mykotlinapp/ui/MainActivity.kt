package com.business.mykotlinapp.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.business.mykotlinapp.Adapters.ListItemAdapter
import com.business.mykotlinapp.MyApplication.Companion.repository
import com.business.mykotlinapp.R
import com.business.mykotlinapp.ViewModels.DataViewModel
import com.business.mykotlinapp.ViewModels.ViewModelsFactory
import com.business.mykotlinapp.databinding.ActivityLoginBinding
import com.business.mykotlinapp.model.ListItems

class MainActivity : AppCompatActivity() {
    lateinit  var binding:ActivityLoginBinding
    lateinit var dataViewModel: DataViewModel
    lateinit var handler: Handler
    lateinit var itemAdapter: ListItemAdapter
    lateinit var body:HashMap<String,String>
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var pbdialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        alertDialog=AlertDialog.Builder(this)
        pbdialog= ProgressDialog(this)
        setupVm()
        handler= Handler(Looper.getMainLooper())
        body=HashMap()
        handler.postDelayed(({
            getDataList()
        }), 500)

        binding.fabAdd.setOnClickListener { v ->

            showAlert()
        }

    }

      private fun getDataList(){

          dataViewModel.getData().observe(this,{liveList->

            liveList?.let { extractedlist->

                if(extractedlist.isNullOrEmpty()){

                   showProgress("Fetching data...")

                } else if (extractedlist.isNotEmpty()){
                    close()
                    inflateData(extractedlist)


                } else{

                    close()
                    Toast.makeText(this,"Something went wrong!",Toast.LENGTH_SHORT).show()
                }


            }
          })



    }

private fun inflateData(list:List<ListItems>){
    binding.recyclerView.layoutManager=LinearLayoutManager( this)
    itemAdapter=ListItemAdapter(list,this)
    if(list.isNotEmpty()){

        binding.recyclerView.adapter=itemAdapter;
    } else{


    }
}

fun showAlert(){

    var dialog:AlertDialog?=null
    var view:View = View.inflate(this,R.layout.alert,null)
    var edTitle:EditText=view.findViewById(R.id.ed_ip)
    var edDesc:EditText=view.findViewById(R.id.ed_desc)
    alertDialog.setView(view)
    alertDialog.setPositiveButton(android.R.string.yes) { dialog, which ->
        if(!edDesc.text.toString().trim().isEmpty()&&!edTitle.text.toString().trim().isEmpty()){

            body.put("title",edTitle.text.toString())
            body.put("desc", edDesc.text.toString())
            postData(body)
            dialog.dismiss()

        } else{
            Toast.makeText(this,"Fill required data!",Toast.LENGTH_SHORT).show()
        }
    }
    alertDialog.setNegativeButton(android.R.string.no) { dialog, which ->

        dialog.dismiss()
    }
    dialog=alertDialog.create()
    dialog.setTitle("Add Data")
    dialog.show()
}

    fun setupVm(){

        dataViewModel=ViewModelProvider(this,ViewModelsFactory(repository)).get(DataViewModel::class.java)
    }

    fun postData(body:HashMap<String,String>){

        dataViewModel.postData(body)?.observe(this,{status->

            status.let{

                if(it==1){

                showProgress("Posting data...")

                } else if(it==200){

                    close()
                    Toast.makeText(this,"data successfully added!",Toast.LENGTH_SHORT).show()
                    handler.postDelayed(({
                        getDataList()
                    }), 500)

                } else if(it==503){

                    close()
                    Toast.makeText(this,"Failed to add data",Toast.LENGTH_SHORT).show()

                }

            }
        })
    }

    fun showProgress( msg:String){
        pbdialog.setMessage(msg)
        pbdialog.setCancelable(false)
        pbdialog.show()

    }

    fun close(){

        if(pbdialog.isShowing){

            pbdialog.cancel()
        }
    }
}