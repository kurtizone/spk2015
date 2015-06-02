function mutationChildren = nonUniformMutation(parents,options,GenomeLength,FitnessFcn,state,thisScore,thisPopulation,mutationRate)
%нерівномірна мутація
mutationChildren = zeros(length(parents),GenomeLength);
    for i=1:length(parents)
        child = thisPopulation(parents(i),:);
        mutationPoints = find(rand(1,length(child)));
        child(mutationPoints) = ~child(mutationPoints);
        mutationChildren(i,:) = child;
    end
    
end