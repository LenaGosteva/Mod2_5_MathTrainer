package com.example.mod2_5;

import static com.example.mod2_5.R.drawable.button;
import static com.example.mod2_5.R.drawable.truec;
import static com.example.mod2_5.R.drawable.wrong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    int counter =3;
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

    @SuppressLint("DefaultLocale")
    private void gener(){

        int pos = problem.getRandom(1, 4);
        binding.problem.setText(problem.getProblem());
        float a = problem.getNotResult();
        float b = problem.getNotResult();
        if (a != problem.getResult() && a == b){
            b = a + problem.getRandom(-10, 0);
            if (b == problem.getResult()) b = b + problem.getRandom(12, 15);
        }
        if (a == problem.getResult() && a == b){
            a = a + problem.getRandom(-10, -1);
            b = b + problem.getRandom(12, 15);
        }

        switch (pos){
            case 1:
                binding.text1.setText(String.format("%.2f", problem.getResult()));
                binding.text.setText(String.format("%.2f", a));
                binding.text2.setText(String.format("%.2f", b));
                break;
            case 2:

                binding.text2.setText(String.format("%.2f", problem.getResult()));
                binding.text1.setText(String.format("%.2f", b));
                binding.text.setText(String.format("%.2f", a));
                break;
            case 3:
                binding.text.setText(String.format("%.2f", problem.getResult()));
                binding.text1.setText(String.format("%.2f", a));
                binding.text2.setText(String.format("%.2f", b));
                break;
        }
    }
    class click implements View.OnClickListener{

        @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables", "NonConstantResourceId"})
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.next:

                    if(fl) {
                        binding.text.setBackground(getDrawable(button));
                        binding.text1.setBackground(getDrawable(button));
                        binding.text2.setBackground(getDrawable(button));
                        gener();
                        counter = 3;
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Вы не можете нажать эту кнопку, введите ответ!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.text1:
                case R.id.text:
                case R.id.text2:
                    String text =  ((TextView)view).getText().toString();
                    if(text.equals(String.format("%.2f",problem.getResult()))){
                        view.setBackground(getDrawable(truec));
                        fl = true;
                    }else{
                        view.setBackground(getDrawable(wrong));
                        fl = false;

                        counter--;
                        if (counter>0){
                            Toast.makeText(MainActivity.this, "У Вас осталось "+ counter+" попыток", Toast.LENGTH_SHORT).show();
                        }
                        if (counter==0){
                            Toast.makeText(MainActivity.this, "У Вас кончились жизни \n Попробуйте другой пример", Toast.LENGTH_SHORT).show();
                            gener();
                        }
                        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                        executorService.schedule(new Runnable() {
                            @Override
                            public void run() {
                                binding.text.setBackground(getDrawable(button));
                                binding.text1.setBackground(getDrawable(button));
                                binding.text2.setBackground(getDrawable(button));
                            }
                        }, 1, TimeUnit.SECONDS);
                    }


                    break;
            }
        }
    }
}