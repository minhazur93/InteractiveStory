package android.minhazur.com.interactivestory.ui;

import android.graphics.drawable.Drawable;
import android.minhazur.com.interactivestory.R;
import android.minhazur.com.interactivestory.model.Page;
import android.minhazur.com.interactivestory.model.Story;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StoryActivity extends AppCompatActivity {

    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choiceOneButton;
    private Button choiceTwoButton;
    private String name;
    Page page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = findViewById(R.id.storyImageView);
        storyTextView = findViewById(R.id.storyTextView);
        choiceOneButton = findViewById(R.id.choiceOneButton);
        choiceTwoButton = findViewById(R.id.choiceTwoButton);

        name = getIntent().getStringExtra(getString(R.string.key_name));

        story = new Story();
        loadPage(0);
    }

    private void loadPage(int pageNumber) {

        Toast.makeText(this, String.valueOf(pageNumber), Toast.LENGTH_SHORT).show();

        page = story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageID());
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextID());
        pageText = String.format(pageText, name);
        storyTextView.setText(pageText);

        if (page.isFinalPage() == false) {
            choiceOneButton.setVisibility(View.VISIBLE);
            loadButtons(page);
        } else {
            choiceOneButton.setVisibility(View.GONE);
            choiceTwoButton.setText("Play Again");
            choiceTwoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadPage(0);
                }
            });
        }
    }

    public void loadButtons(final Page page) {
        choiceOneButton.setText(page.getChoice1().getTextID());
        choiceTwoButton.setText(page.getChoice2().getTextID());

        choiceOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPage(page.getChoice1().getNextPage());
            }
        });
        choiceTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadPage(page.getChoice2().getNextPage());
            }
        });
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            loadPage(page.getPageNumber()-1);
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }


}
