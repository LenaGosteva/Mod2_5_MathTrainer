package com.example.mod2_5;

import static com.example.mod2_5.R.drawable.button;
import static com.example.mod2_5.R.drawable.truec;
import static com.example.mod2_5.R.drawable.wrong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mod2_5.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private final Problem problem = new Problem();

    private ActivityMainBinding binding;
    boolean fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fl = false;
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gener();
        click click = new click();
        binding.next.setOnClickListener(click);
        binding.text.setOnClickListener(click);
        binding.text1.setOnClickListener(click);
        binding.text2.setOnClickListener(click);
    }

    private void gener(){

        int pos = problem.getRandom(1, 4);
        binding.problem.setText(problem.getProblem());

        switch (pos){
            case 1:
                binding.text1.setText(String.format("%.2f", problem.getResult()));
                binding.text.setText(String.format("%.2f", problem.getNotResult()));
                binding.text2.setText(String.format("%.2f", problem.getNotResult()));
                break;
            case 2:

                binding.text2.setText(String.format("%.2f", problem.getResult()));
                binding.text1.setText(String.format("%.2f", problem.getNotResult()));
                binding.text.setText(String.format("%.2f", problem.getNotResult()));
                break;
            case 3:
                binding.text.setText(String.format("%.2f", problem.getResult()));
                binding.text1.setText(String.format("%.2f", problem.getNotResult()));
                binding.text2.setText(String.format("%.2f", problem.getNotResult()));
                break;
        }
    }
    class click implements View.OnClickListener{

        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.next:

                    if(fl) {
                        binding.text.setBackground(getDrawable(button));
                        binding.text1.setBackground(getDrawable(button));
                        binding.text2.setBackground(getDrawable(button));
                        gener();
                    }

                    break;
                case R.id.text1:
                case R.id.text:
                case R.id.text2:
                    String text =  ((TextView)view).getText().toString();
                    if(text.equals(String.valueOf(problem.getResult()))){
                        view.setBackground(getDrawable(truec));
                        fl = true;
                    }else{
                        view.setBackground(getDrawable(wrong));
                        Toast.makeText(MainActivity.this, "Подумайте еще!", Toast.LENGTH_SHORT).show();
                        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                        executorService.scheduleAtFixedRate(new Runnable() {
                            @Override
                            public void run() {
                                binding.text.setBackground(getDrawable(button));
                                binding.text1.setBackground(getDrawable(button));
                                binding.text2.setBackground(getDrawable(button));
                            }
                        }, 3, 1, TimeUnit.SECONDS);
                    }


                    break;
            }
        }
    }
}