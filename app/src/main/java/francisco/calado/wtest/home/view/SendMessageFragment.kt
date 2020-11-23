package francisco.calado.wtest.home.view

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import francisco.calado.wtest.R
import francisco.calado.wtest.home.model.SendMessageErrorState
import francisco.calado.wtest.home.model.SendMessageViewState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_send_message.*
import java.text.SimpleDateFormat
import java.util.*


class SendMessageFragment : Fragment(), SendMessageView {

    private val spinnerValues =
        arrayOf("Rating", "Bad", "Satisfactory", "Good", "Very Good", "Excellent")

    private lateinit var presenter: SendMessagePresenter
    private lateinit var submitClickSubject: PublishSubject<SendMessageViewState>
    private lateinit var dateListener: DatePickerDialog.OnDateSetListener
    private val calendar = Calendar.getInstance()


    companion object {
        fun newInstance() = SendMessageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send_message, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        submitClickSubject = PublishSubject.create()
        presenter = SendMessagePresenter(this, CompositeDisposable())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            setValue(calendar)
        }

        date.setOnClickListener { handleDatePicker() }

        submit_button.setOnClickListener {
            submitClickSubject.onNext(
                SendMessageViewState(
                    message.text.toString(),
                    email.text.toString(),
                    number.text.toString(),
                    letter_code.text.toString(),
                    date.text.toString(),
                    rating.selectedItem.toString()
                )
            )
        }
        val spinnerAdapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            spinnerValues
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int, convertView: View?,
                parent: ViewGroup
            ): View {
                val dropDownView = super.getDropDownView(position, convertView, parent)
                val textView = dropDownView as TextView
                if (position == 0) {
                    textView.setTextColor(Color.GRAY)
                } else {
                    textView.setTextColor(Color.WHITE)
                }
                return dropDownView
            }
        }
        rating.adapter = spinnerAdapter
    }

    private fun setValue(calendar: Calendar) {
        val myFormat = "EEEE, dd MMMM yyyy"
        val format = SimpleDateFormat(myFormat, Locale.US)

        date.text = format.format(calendar.time)

    }

    private fun handleDatePicker() {
        DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun submitClicked(): Observable<SendMessageViewState> {
        return submitClickSubject
    }

    override fun onResume() {
        super.onResume()
        presenter.validateInputs()
    }

    override fun setErrorState(viewState: SendMessageErrorState) {
        var submit = true

        //Handle Message State
        if (viewState.message.isNotEmpty()) {
            message_error.visibility = View.VISIBLE
            submit = false
        } else
            message_error.visibility = View.GONE

        //Handle email State
        if (viewState.email.isNotEmpty()) {
            email_error.visibility = View.VISIBLE
            submit = false
        } else
            email_error.visibility = View.GONE

        //Handle number State
        if (viewState.number.isNotEmpty()) {
            number_error.visibility = View.VISIBLE
            submit = false
        } else
            number_error.visibility = View.GONE

        //Handle code State
        if (viewState.code.isNotEmpty()) {
            code_error.visibility = View.VISIBLE
            submit = false
        } else
            code_error.visibility = View.GONE

        //Handle date State
        if (viewState.date.isNotEmpty()) {
            date_error.visibility = View.VISIBLE
            submit = false
        } else
            date_error.visibility = View.GONE

        //Handle rating State
        if (viewState.rating.isNotEmpty()) {
            rating_error.visibility = View.VISIBLE
            submit = false
        } else
            rating_error.visibility = View.GONE

        if (submit) {
            requireActivity().onBackPressed()
        }
    }
}