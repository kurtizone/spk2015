%% Defining borders. File 'usborder.mat' defining this borders.
load('circleBorder.mat','x','y','xx','yy');
plot(x,y,'Color','red'); hold on;

%%
% Generating random locations of cities inside the border. 
% We use the INPOLYGON function to make sure that all
% the cities are inside or very close to the boundary.
cities = 10;
locations = zeros(cities,2);
n = 1;
while (n <= cities)
    xp = rand*1.5;
    yp = rand;
    if inpolygon(xp,yp,xx,yy)
        locations(n,1) = xp;
        locations(n,2) = yp;
        n = n+1;
    end
end
plot(locations(:,1),locations(:,2),'bo');

%%
% Given the list of city locations, 
% we can calculate the distance matrix for all the cities.
distances = zeros(cities);
for count1=1:cities,
    for count2=1:count1,
        x1 = locations(count1,1);
        y1 = locations(count1,2);
        x2 = locations(count2,1);
        y2 = locations(count2,2);
        distances(count1,count2)=sqrt((x1-x2)^2+(y1-y2)^2);
        distances(count2,count1)=distances(count1,count2);
    end;
end;

%%
% The custom crossover function takes a cell array, the population, and
% returns a cell array, the children that result from the crossover.
type PMXSalesman.m

%%
% The custom mutation function takes an individual, which is an ordered set
% of cities, and returns a mutated ordered set.
type invert_classic_mutation.m

%%
% Fitness function for the traveling salesman problem. The
% fitness of an individual is the total distance traveled for an ordered
% set of cities. The fitness function also needs the distance matrix to
% calculate the total distance.
type salesmanFitness.m

%%
% GA call fitness function with just arguments 'x' and 'distances'. 
% We can use an anonymous function to capture the values of the additional argument, 
% the distances matrix. We create a function handle 'FitnessFcn' to an anonymous function
% that takes one input 'x', but calls 'traveling_salesman_fitness' with x,
% and distances. The variable, distances has a value when the function handle
% 'FitnessFcn' is created, so these values are captured by the anonymous function.
FitnessFcn = @(x) salesmanFitness(x,distances);

%%
% A custom plot function to plot the location of the cities and the current best route.
type plotSalesman.m

%%
% Once again we will use an anonymous function to create a function handle
% to an anonymous function which calls 'traveling_salesman_plot' with the
% additional argument 'locations'.
my_plot = @(options,state,flag) plotSalesman(options, ...
    state,flag,locations);

%% Genetic Algorithm Options Setup
% This is an options structure to indicate a custom data type
% and the population range.
options = gaoptimset('PopulationType', 'custom','PopInitRange', ...
    [1;cities]);

%%
% Choose the custom creation, crossover, mutation, and plot functions that
% we have created, as well as setting some stopping conditions.
timeBegin = clock;
options = gaoptimset(options,'CreationFcn',@createSalesman, ...
    'CrossoverFcn',@PMXSalesman, ...
    'MutationFcn',@invert_classic_mutation, ...
    'PlotFcn', my_plot, ...
    'Generations',500,'PopulationSize',100, ...
    'StallGenLimit',500,'Vectorized','on');
%%
% Finally, we call the genetic algorithm with our problem information.

numberOfVariables = cities;
[x,fval,reason,output] = ga(FitnessFcn,numberOfVariables,options)

timeEnd = clock;
time=timeBegin-timeEnd %знаходження часу роботи оптимізації
 


