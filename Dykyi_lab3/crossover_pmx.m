function xoverKids  = crossover_pmx(parents,options,NVARS, ...
    FitnessFcn,thisScore,thisPopulation)
%схрещування pmx
nKids = length(parents)/2;
xoverKids = cell(nKids,1); % Normally zeros(nKids,NVARS);
index = 1;
for i=1:nKids
    %вибір бітьків
    parent1 = thisPopulation{parents(index)};
    if(parents(index) == length(thisPopulation))
        parent2 = thisPopulation{parents(index)-1};
    else 
        parent2 = thisPopulation{parents(index)+1};
    end
    %генерування дві точки схрещування
     p1 = randi([1 length(parent1)],1,1);
     p2 = randi([p1 length(parent1)],1,1);
     child = parent1; 
     for j = p1:p2
         item1 = parent1(j);
         item2 = parent2(j);    
         for k = 1:length(parent2)
             if(child(k) == item1) pos1 = k;
             else if (child(k) == item2) pos2 = k;
                 end
             end
         end 
         if(item1 ~= item2) 
             child(pos1)=item2;
             child(pos2)=item1;
         end 
     end
     xoverKids{i} = child;
     index = index + 2;
end
