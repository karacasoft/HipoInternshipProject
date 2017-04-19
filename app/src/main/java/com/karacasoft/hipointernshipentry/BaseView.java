package com.karacasoft.hipointernshipentry;

/**
 * Created by mahmutkaraca on 4/18/17.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
    void showError(String message);
    void showError(Throwable t);

}
