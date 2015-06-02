
function xoverKids  = comparison_crossover(parents, options, nvars, FitnessFcn, ... 
    unused,thisPopulation)%порівняльне схрещування
    leng = length(parents)/2;
    for j = 1:nvars
    for i = 1:leng
        if (parents(i) == parents(i + 14))
            xoverKids(i,j) = parents(i);
        else
            k = rand();
            if (k <= 0.5) xoverKids(i,j) = parents(i);
            else xoverKids(i,j) = parents(i + 14);
            end         
        end
    end
    end 
end