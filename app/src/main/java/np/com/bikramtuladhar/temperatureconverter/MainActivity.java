package np.com.bikramtuladhar.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = findViewById(R.id.tlCaculator);
        TextView expression = findViewById(R.id.expression);
        TextView result = findViewById(R.id.result);

        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            View row = tableLayout.getChildAt(i);
            if (row instanceof TableRow) {
                TableRow tableRow = (TableRow) row;
                for (int j = 0; j < tableRow.getChildCount(); j++) {
                    View view = tableRow.getChildAt(j);
                    if (view instanceof Button) {
                        Button button = (Button) view;

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String buttonText = ((Button) v).getText().toString();

                                if (buttonText.equals("CE")) {
                                    expression.setText("");
                                    result.setText("");
                                    return;
                                }

                                if (buttonText.equals("Delete")) {
                                    String text = expression.getText().toString();
                                    if (text.length() > 0) {
                                        expression.setText(text.substring(0, text.length() - 1));
                                    }
                                    return;
                                }

                                if (buttonText.equals("C-F")) {
                                    float calResult = 0;

                                    try {
                                        calResult = convertCtoF(Float.parseFloat(expression.getText().toString()));
                                    } catch (Exception e) {
                                        result.setText("ERR");
                                    }

                                    result.setText(String.valueOf(calResult)+'F');
                                    return;
                                }

                                if (buttonText.equals("F-C")) {
                                    float calResult = 0;

                                    try {
                                        calResult = convertFtoC(Float.parseFloat(expression.getText().toString()));
                                    } catch (Exception e) {
                                        result.setText("ERR");
                                    }

                                    result.setText(String.valueOf(calResult)+'C');
                                    return;
                                }

                                String exp = expression.getText() + buttonText;

                                expression.setText(exp);
                            }
                        });
                    }
                }
            }
        }
    }

    private float convertCtoF(float c) {
        return (c * 9 / 5) + 32;
    }

    private float convertFtoC(float f) {
        return (f - 32) * 5 / 9;
    }
}