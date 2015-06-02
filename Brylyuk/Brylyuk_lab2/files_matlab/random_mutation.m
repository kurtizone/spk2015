function mutationChildren = random_mutation(parents ,options,NVARS, ...
    FitnessFcn, state, thisScore,thisPopulation);
T=100;
ti=state.Generation;
a=2.0*randn*((log10(T)-log10(ti))/log10(T));
b=0;
deltaH=randn/(a-b)+b;
thisPopulation
     for i=1:length(parents)
         child=thisPopulation(parents(i),:);
         mutationPoints=find(rand(1,length(child))<0.01);
         child(mutationPoints)=~child(mutationPoints);
         mutationChildren(i,:)= child+deltaH;
     end
 end
