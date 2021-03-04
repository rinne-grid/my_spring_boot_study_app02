package jp.co.rngd.ss.todo.entity;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import jp.co.rngd.ss.todo.consts.FilterSetting;
import jp.co.rngd.ss.todo.consts.SortSetting;
import jp.co.rngd.ss.todo.consts.TodoConst;
import jp.co.rngd.ss.todo.models.TodoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoSearchCondition implements SearchCondition {
    private String filterSetting;
    private String sortSetting;
    
    public static TodoSearchCondition of(String sortSetting) {
        return new TodoSearchCondition(sortSetting);
    }
    
    public static TodoSearchCondition of(String sortSetting, String filterSetting) {
        return new TodoSearchCondition(sortSetting, filterSetting);
    }
    
    public TodoSearchCondition(String sortSetting) {
        this.sortSetting = sortSetting;
    }
    
    public TodoSearchCondition(String sortSetting, String filterSetting) {
        this.sortSetting = sortSetting;
        this.filterSetting = filterSetting;
    }
    
    public boolean isValidFilter() {
        boolean result = false;
        for(FilterSetting f : FilterSetting.values()) {
            if(f.name().equals(this.filterSetting)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public boolean isValidSort() {
        boolean result = false;
        for(SortSetting s : SortSetting.values()) {
            if(s.name().equals(this.sortSetting)) {
                result = true;
                break;
            }
        }
        return result;
    }
   
    /***
     * 検索条件を取得します
     */
    public Object filter(Object todoObj) {
        TodoModel todo = (TodoModel)todoObj;
        if(this.isValidFilter()) {
            switch(this.filterSetting) {
            case TodoConst.FILTER_SETTING_DISPLAY_COMPLETED:
                todo.setCompleted(true);
                break;
            case TodoConst.FILTER_SETTING_DISPLAY_UNCOMPLETED:
                todo.setCompleted(false);
                break;
            }
        }
        return todo;
    }
    
    /***
     * ソート順序を取得します
     */
    public Sort orderBy() {
        Sort sort = null;
        if(this.isValidSort()) {
            switch(this.sortSetting) {
                case TodoConst.SORT_SETTING_START_DATE_DESC:
                    sort = Sort.by(Direction.DESC, TodoConst.DB_COLUMN_TODO_START_DATE);
                    break;
                case TodoConst.SORT_SETTING_START_DATE_ASC:
                    sort = Sort.by(Direction.ASC, TodoConst.DB_COLUMN_TODO_START_DATE);
                    break;
                case TodoConst.SORT_SETTING_END_DATE_DESC:
                    sort = Sort.by(Direction.DESC, TodoConst.DB_COLUMN_TODO_END_DATE);
                    break;
                case TodoConst.SORT_SETTING_END_DATE_ASC:
                    sort = Sort.by(Direction.ASC, TodoConst.DB_COLUMN_TODO_END_DATE);
                    break;
            }
        } else {
            // デフォルトの場合、開始日の降順にする
            sort = Sort.by(Direction.DESC, TodoConst.DB_COLUMN_TODO_START_DATE);
        }
        return sort;
    }

}
