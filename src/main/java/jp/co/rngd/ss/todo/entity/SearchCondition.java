package jp.co.rngd.ss.todo.entity;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

public interface SearchCondition {
    public Object filter(Object object);
    public Sort orderBy();
    public boolean isValidFilter();
    public boolean isValidSort();
}
