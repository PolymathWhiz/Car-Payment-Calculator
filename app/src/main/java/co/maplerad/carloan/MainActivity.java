package co.maplerad.carloan;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.SeekBar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Button;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private SeekBar mySeekBar;
    private Button myButton;
    private GridLayout results;
    private EditText carLoan, downPayment;
    private TextView twentyFourRes,
                     interestRate,
                     thirtySixRes,
                     fortyEightRes,
                     sixtyRes,
                     headResult;
    private double carLn = 0,
                   downPay = 0,
                   myProgress = 0.15,
                   mainLoan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySeekBar = findViewById(R.id.seekBarId);
        interestRate = findViewById(R.id.interestRateId);
        carLoan = findViewById(R.id.carPriceId);
        downPayment = findViewById(R.id.downPaymentId);
        twentyFourRes = findViewById(R.id.twentyFourResult);
        thirtySixRes = findViewById(R.id.thirtySixResult);
        fortyEightRes = findViewById(R.id.fortyEightResult);
        sixtyRes = findViewById(R.id.sixtyResult);
        myButton = findViewById(R.id.buttonId);
        results = findViewById(R.id.results);
        headResult = findViewById(R.id.headingResult);

        interestRate.setText(percentFormat.format(myProgress));

        mySeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myProgress = progress/100.0;
                interestRate.setText(percentFormat.format(myProgress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(carLoan.getText().toString()).isEmpty()){
                    carLn = Double.parseDouble(carLoan.getText().toString());
                }

                if(!(downPayment.getText().toString()).isEmpty()){
                    downPay = Double.parseDouble(downPayment.getText().toString());
                }
                mainLoan = carLn-downPay;
                mainLoan = mainLoan + (mainLoan * myProgress);

                DecimalFormat decimalFormat = new DecimalFormat("#.00");

                double twentyFour = (mainLoan/24);
                twentyFourRes.setText(toString().valueOf(decimalFormat.format(twentyFour)));
                double thirtySix =  (mainLoan/36);
                thirtySixRes.setText(toString().valueOf(decimalFormat.format(thirtySix)));
                double fortyEight = (mainLoan/48);
                fortyEightRes.setText(toString().valueOf(decimalFormat.format(fortyEight)));
                double sixty = (mainLoan/60);
                sixtyRes.setText(toString().valueOf(decimalFormat.format(sixty)));

                headResult.setVisibility(View.VISIBLE);
                results.setVisibility(View.VISIBLE);

            }
        });
    }
}
