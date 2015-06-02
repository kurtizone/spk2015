function mutationChildren = singlePointMutation(parents ,options,NVARS, ...
    FitnessFcn, state, thisScore,thisPopulation,mutationRate)
% Одноточкова мутація обміну.
mutationChildren = cell(length(parents),1);
numberOfGenes=length(thisPopulation{parents(1)});
for i=1:length(parents)
    parent = thisPopulation{parents(i)}; 
   XorPoint=mod(ceil(rand(1)*10),numberOfGenes );
if XorPoint==0
      XorPoint=XorPoint+1;
end
    child = parent;
    child(XorPoint) = parent(XorPoint+1);
    child(XorPoint+1) = parent(XorPoint);
    mutationChildren{i} = child; 
end
