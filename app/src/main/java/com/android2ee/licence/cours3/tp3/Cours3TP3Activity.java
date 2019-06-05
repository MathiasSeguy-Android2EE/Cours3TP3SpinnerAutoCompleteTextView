/**<ul>
 * <li>Cours3TP3SpinnerAutoCompleteTextView</li>
 * <li>com.android2ee.licence.cours3.tp3</li>
 * <li>1 févr. 2012</li>
 * 
 * <li>======================================================</li>
 *
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 *
 /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br> 
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage expect training and can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * 
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br> 
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */
package com.android2ee.licence.cours3.tp3;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 * This class aims to show how to use spinner and autoCompleteTextView
 * <ul><li></li></ul>
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to show the usage of a spinner and an AutoCompleteTextView
 *        <ul>
 *        <li></li>
 *        </ul>
 */
public class Cours3TP3Activity extends Activity {
	/******************************************************************************************/
	/** Attributes **************************************************************************/
	/******************************************************************************************/

	/** * The textView that displays the value of the spinner */
	TextView txvItemSelected;
	/** * A textView that displays the value of the autocomplete text view */
	TextView txvAutoCompValue;
	/** * An AutoComplete TextView */
	AutoCompleteTextView autoCompleteTextView;
	/** *The arrayAdapter that manage the data displayed by the AutoComplete */
	ArrayAdapter<String> arrayAdapter;
	/** * The Spinner */
	Spinner spinner;
	/** * The arrayAdapter that manage the data displayed by the Spinner */
	ArrayAdapter<CharSequence> spinnerArrayAdapter;

	/******************************************************************************************/
	/** Constructors **************************************************************************/
	/******************************************************************************************/

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// retrieve the spinner and its text view
		spinner = (Spinner) findViewById(R.id.spinner);
		txvItemSelected = (TextView) findViewById(R.id.txvItemSelected);
		// create the Adapter from the ressources
		spinnerArrayAdapter = ArrayAdapter.createFromResource(this, R.array.planets_array,
				android.R.layout.simple_spinner_item);
		// define how the spinner is opened: You can choose :
		// simple_spinner_item to open a small window of selection
		// simple_spinner_dropdown_item to open a window of selection
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// link the spinner and its adapter
		spinner.setAdapter(spinnerArrayAdapter);

		// create the AutoCompleteTextView
		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
		autoCompleteTextView.setTextColor(0xFF00FF00);
		txvAutoCompValue = (TextView) findViewById(R.id.txvAutoCompValue);
		// Define the Adapter (Context, ListView Ressource, The items to display)
		String[] items = getResources().getStringArray(R.array.planets_array);
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);
		autoCompleteTextView.setAdapter(arrayAdapter);

		// Add the listeners on the Spinner and the AutoCompleteTextView
		addListeners();
	}

	/******************************************************************************************/
	/** Listeners Methods **************************************************************************/
	/******************************************************************************************/

	/**
	 * This method adds listeners on the spinner and the autoCompleteTextView
	 */
	private void addListeners() {
		// add the listener to the spinner that listen for item selection
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				itemSelected(pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// Not used
			}
		});
		// Add the this.TextWatcher as listener of the EditText
		autoCompleteTextView.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				textChanged(s, start, before, count);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// Not used
			}

			@Override
			public void afterTextChanged(Editable s) {
				// Not used
			}
		});
	}

	/**
	 * The method called when an item is selected in the Spinner
	 * 
	 * @param position
	 */
	private void itemSelected(int position) {
		CharSequence spinnerValue = spinnerArrayAdapter.getItem(position);
		txvItemSelected.setText(spinnerValue);
	}

	/**
	 * The method called when the value of the Edit text is changed
	 * This method is called to notify you that, within s, the count characters beginning at start
	 * have just replaced old text that had length before. It is an error to attempt to make changes
	 * to s from this callback.
	 * FR:La chaine s provient de la modification de la chaine précédente qui a remplacé count
	 * caractères entre le caractères start et le caractère before de la chaine de caractères
	 * précédentes.
	 * 
	 * @param s
	 *            The value of the displayed string
	 * @param start
	 *            the first index of the character that changed in the previous string
	 * @param before
	 *            the last index of the character that changed in the previous string
	 * @param count
	 *            the number of characters added between those two indexes
	 */
	public void textChanged(CharSequence s, int start, int before, int count) {
		txvAutoCompValue.setText(s + "(" + start + "," + before + "," + count + ")");
	}

}