package com.softwarica.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softwarica.employeeapi.api.EmployeeAPI;
import com.softwarica.employeeapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployeeActivity extends AppCompatActivity {

    private final static String BASE_URL = "http://dummy.restapiexample.com/api/v1/";
    private EditText etSearch;
    private TextView tvData;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);


        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        tvData = findViewById(R.id.tvData);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaddata();

            }
        });
    }

    private void loaddata() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etSearch.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchEmployeeActivity.this, "Error code" + response.code(), Toast.LENGTH_SHORT).show();

                String data = "";
                data += "ID is: :" + response.body().getId() + "\n";
                data += "Salary is :" + response.body().getEmployee_salary() + "\n";
                data += "Name is:" + response.body().getEmployee_name() + "\n";
                data += "------------" + "\n";
                tvData.append(data);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

            }
        });
    }
}








