function xoverKids  = PMXSalesman(parents,options,NVARS, ...
    FitnessFcn,thisScore,thisPopulation)
%   CROSSOVER_PERMUTATION Custom crossover function for traveling salesman. 
nKids = length(parents)/2;
xoverKids = cell(nKids,1); % Normally zeros(nKids,NVARS);
index = 1;
kidsIterator=1;
numberOfGenes=length(thisPopulation{parents(index)});
%% For all selected parents do crossovering
while kidsIterator<nKids
    parent1 = thisPopulation{parents(index)};
    index = index + 1;
    parent2 = thisPopulation{parents(index)};
    index = index + 1;
%% Step1. Define random point
   XorPoint=mod(ceil(rand(1)*10),numberOfGenes );
if XorPoint==0
      XorPoint=XorPoint+1;
end

%% Step2. Initialize first child
    child1=parent2;

%% Step3. Defining first part of child1's genes 
    l=XorPoint+1;
for firstPartIterator=1:XorPoint
if ismember(parent1(firstPartIterator), parent2((XorPoint+1):numberOfGenes))==0
            child1(firstPartIterator)=parent1(firstPartIterator);
else
for secondPartIterator=l:numberOfGenes
if ismember(parent1(secondPartIterator), parent2((XorPoint+1):numberOfGenes))==0
                    child1(firstPartIterator)=parent1(secondPartIterator); l=l+1; break;
else
                    l=l+1;
end
end
end
end
%%
    xoverKids{kidsIterator} = child1; % Normally, xoverKids(i,:);
    kidsIterator=kidsIterator+1;
    child2=parent1;
    xoverKids{kidsIterator} = child2; % Normally, xoverKids(i,:);
    kidsIterator=kidsIterator+1;
end


