package peoplepictures.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
public class ResultPaginator {

    public List paginateResults(List list, Integer limit, Integer start){
        // Adjust limits of limit and start parameters
        if(limit==null || limit < 0){
            limit = list.size();
        }
        if(start == null || start < 0 || start > list.size()){
            start = 0;
        }

        // Obtain to index
        Integer toIndex = (start + limit);
        if (toIndex > list.size()){
            toIndex = list.size();
        }
        // return a sub list from the main list
        return list.subList(start, toIndex);
    }
}
