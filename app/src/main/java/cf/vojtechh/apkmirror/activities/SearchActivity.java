package cf.vojtechh.apkmirror.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import cf.vojtechh.apkmirror.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        new MaterialDialog.Builder(this)
                .title(R.string.search)
                .inputRange(1, 100)
                .theme(Theme.LIGHT)
                .negativeText(android.R.string.cancel)
                .input(R.string.search, R.string.nothing, (dialog, input) -> {})
                .onPositive((dialog, which) -> {
                    Intent i = new Intent(SearchActivity.this, MainActivity.class);
                    if (dialog.getInputEditText() != null) {
                        i.putExtra("url", "https://www.apkmirror.com/?s=" + dialog.getInputEditText().getText());
                    } else {
                        Toast.makeText(SearchActivity.this, getString(R.string.search_error), Toast.LENGTH_SHORT).show();
                    }
                    startActivity(i);
                    finish();
                })
                .onNegative((dialog, which) -> finish())
                .show();
    }
}
