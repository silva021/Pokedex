package com.silva021.pokedex.presenter.ui.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.silva021.pokedex.R;
import com.silva021.pokedex.domain.model.menu.Menu;
import com.silva021.pokedex.presenter.adapter.MenuAdapter;
import com.silva021.pokedex.presenter.adapter.PokemonAdapter;
import com.silva021.pokedex.presenter.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity implements MenuAdapter.OnClickListener {
    private MenuViewModel mViewModel;
    private MenuAdapter mAdapter;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private List<Menu> menuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        initRecycler();
    }

    private void initRecycler() {
        mAdapter = new MenuAdapter(menuList);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter.setListener(this);

        mViewModel.menuRepository.getListMenu().observe(this, menus -> {
            mAdapter.setData(menus);
        });

    }

    @Override
    public void onClickListener(Menu menu) {
        startActivity(new Intent(this, menu.getActivityClass()));
//        finish();
    }
}