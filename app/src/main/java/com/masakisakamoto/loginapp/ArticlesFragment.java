package com.masakisakamoto.loginapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.masakisakamoto.loginapp.domain.Article;

import java.util.List;

/**
 * Created by masakisakamoto on 8/1/17.
 */

public class ArticlesFragment extends Fragment {

    public static final String GROUP_ID = "group_id";

    private String groupId = "0";

    private GetArticleUseCase getArticleUseCase;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArticleViewAdapter adapter;
    private ArticleRepository articleRepository;
    private TextView textView;

    public static ArticlesFragment newInstance(String groupId) {
        ArticlesFragment articlesFragment = new ArticlesFragment();
        Bundle b = new Bundle();
        b.putString(GROUP_ID, groupId);
        articlesFragment.setArguments(b);
        return articlesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arg = getArguments();
        if (arg != null  || arg.isEmpty()) {
            groupId = getArguments().getString(GROUP_ID);{
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout. fragment_main, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        textView = (TextView) view.findViewById(R.id.text_title);
        init();
    }

    private void init() {
        // TODO: 8/1/17
        getArticleUseCase = new GetArticleUseCase(getContext());
        articleRepository = new ArticleRepositoryImpl();
        getArticleUseCase.get(groupId, new GetArticleUseCase.OnLoadingListener() {
            @Override
            public void onSuccess(final List<Article> articles) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 取得した結果0個だった場合
                        int size = articles.size();
                        if (0 == size) {
                            // 何もありませんを表示
                            textView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        } else {

                            progressBar.setVisibility(View.GONE);
                            articleRepository.createAll(articles);
                            adapter = new ArticleViewAdapter(getContext(), articles, new ArticleViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    int id = articles.get(position).id;
                                    startActivity(ArticleDetailActivity.newIntent(getContext(), id));
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
            }

            @Override
            public void onError(final String content) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        //showToast(content);
                    }
                });
            }
        });
    }
}
