package IE683.similarity;

import de.uni_mannheim.informatik.dws.winter.similarity.SimilarityMeasure;
import de.uni_mannheim.informatik.dws.winter.similarity.list.GeneralisedJaccard;

import java.util.Collection;
import java.util.List;

public class GeneralisedJaccardNullCompatible<T extends Comparable<? super T>> extends GeneralisedJaccard<T> {
    public GeneralisedJaccardNullCompatible(SimilarityMeasure<T> innerSimilarity, double innerSimilarityThreshold) {
        super(innerSimilarity, innerSimilarityThreshold);
    }

    @Override
    public double calculate(Collection<T> first, Collection<T> second) {
        first = first == null ? List.of() : first;
        second = second == null ? List.of() : second;
        return super.calculate(first, second);
    }
}