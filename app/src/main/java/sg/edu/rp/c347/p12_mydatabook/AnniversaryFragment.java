package sg.edu.rp.c347.p12_mydatabook;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnniversaryFragment extends Fragment {

    Button btnAnni;
    TextView tvAnni;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference messagePOJOReference;
    public AnniversaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_anniversary, container, false);
        tvAnni = (TextView)view.findViewById(R.id.tvAnni);
        firebaseDatabase = FirebaseDatabase.getInstance();
        messagePOJOReference = firebaseDatabase.getReference("anniversary");
        messagePOJOReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String task = dataSnapshot.getValue(String.class);
                Log.i("Check", task);
                tvAnni.setText(task);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method will be invoked if there is any error
                Log.e("Error ", "Database error occurred", databaseError.toException());
            }
        });
        btnAnni = (Button)view.findViewById(R.id.btnAnni);
        btnAnni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflaters = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout passPhrase =
                        (LinearLayout) inflaters.inflate(R.layout.edittext, null);
                final EditText et = (EditText) passPhrase
                        .findViewById(R.id.et);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Anniversary")
                        .setView(passPhrase)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String text = et.getText().toString();
                                messagePOJOReference.setValue(text);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        return view;
    }

}
