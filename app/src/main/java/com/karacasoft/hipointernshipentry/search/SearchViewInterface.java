package com.karacasoft.hipointernshipentry.search;

/**
 * Created by mahmutkaraca on 4/20/17.
 */

public interface SearchViewInterface {

    void onClose();

    boolean onSearch(String text);

    boolean onTextChanged(String newText);

}
