package com.example.quizapp;

import static android.graphics.Color.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView totaque;
    TextView questiontextview;
    Button ans1,ans2,ans3,ans4;
    Button submitbtn;
    int score=0;
    int totalquestion= Questionanswer.question.length;
    int currentquestionindex=0;
    String selectedanswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totaque =findViewById(R.id.totalque);
        questiontextview=findViewById(R.id.question);
        ans1=findViewById(R.id.ans1);
        ans2=findViewById(R.id.ans2);
        ans3=findViewById(R.id.ans3);
        ans4=findViewById(R.id.ans4);
  submitbtn=findViewById(R.id.submit);

  ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
        submitbtn.setOnClickListener(this);

        totaque.setText("Total question:"+totalquestion);
        loadNewquestion();


    }

    private void loadNewquestion() {

        if(currentquestionindex==totalquestion){
            finishquiz();
            return;

        }
        questiontextview.setText(Questionanswer.question[currentquestionindex]);
        ans1.setText(Questionanswer.choice[currentquestionindex][0]);
        ans2.setText(Questionanswer.choice[currentquestionindex][1]);
        ans3.setText(Questionanswer.choice[currentquestionindex][2]);
        ans4.setText(Questionanswer.choice[currentquestionindex][3]);


    }

    private void finishquiz() {
        String passstatus="";
        if(score >totalquestion*0.60){
            passstatus="PASSED";
        }
        else{
            passstatus="FAILED";
        }
        new AlertDialog.Builder(this)
                .setTitle(passstatus)
                .setMessage("score is"+score+"out of"+totalquestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();


    }

    private void restartQuiz() {
        score=0;
        currentquestionindex=0;
        loadNewquestion();
    }

    @Override
    public void onClick(View v) {




     Button clickedbutton=(Button) v;
        ans1.setBackgroundColor(Color.WHITE );
        ans2.setBackgroundColor(Color.WHITE);
        ans3.setBackgroundColor(Color.WHITE);
        ans4.setBackgroundColor(Color.WHITE);
     if(clickedbutton.getId()==R.id.submit){

         if(selectedanswer.equals(Questionanswer.correctanswer[currentquestionindex])){
             score++;
         }
         currentquestionindex++;
         loadNewquestion();


     }
     else{
         selectedanswer=clickedbutton.getText().toString();

         clickedbutton.setBackgroundColor(Color.MAGENTA);


     }


    }
}