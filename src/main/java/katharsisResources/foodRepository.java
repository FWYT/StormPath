package katharsisResources;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.*;

import javax.annotation.security.DenyAll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vagrant on 8/11/16.
 */

@JsonApiResourceRepository(foodResource.class)
public class foodRepository {

    @JsonApiSave
    public <S extends foodResource> S save(S entity) {
        return null;
    }

    @JsonApiFindOne
    public foodResource findOne(Long id) {
        foodResource res = new foodResource();
        res.setId(Long.MAX_VALUE);
        res.setName("Mackerel");
        return res;
    }

    @JsonApiFindAll
    public Iterable<foodResource> findAll(QueryParams queryParams) {
        foodResource res = new foodResource();
        res.setId(Long.MAX_VALUE);
        res.setName("Mackerel");
        foodResource res2 = new foodResource();
        res.setId(Long.MAX_VALUE-1);
        res.setName("Bass");
        List<foodResource> ress = Arrays.asList(res, res2);
        return ress;
    }

    @JsonApiFindAllWithIds
    public Iterable<foodResource> findAll(Iterable<Long> iterable, QueryParams queryParams) {
        return null;
    }

    @JsonApiDelete
    public void delete(Long id) {
    }
}
