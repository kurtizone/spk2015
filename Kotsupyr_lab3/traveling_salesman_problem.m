cities = 40;%кількість міст
locations = zeros(cities,2);
mas = zeros(cities,2);
x=cell(1,1);
for n = 1: cities%генерування розташування міст
    locations(n,1) = randi([0 20],1,1);
    mas(1,n) = locations(n,1);
    locations(n,2) = randi([0 20],1,1);
     
end
x{1}=mas(1,:);

plot(locations(:,1),locations(:,2),'bo')

distances = zeros(cities);
for count1=1:cities,%знаходження відстані між містами
    for count2=1:count1,
        x1 = locations(count1,1);
        y1 = locations(count1,2);
        x2 = locations(count2,1);
        y2 = locations(count2,2);
        distances(count1,count2)=sqrt((x1-x2)^2+(y1-y2)^2);
        distances(count2,count1)=distances(count1,count2);
    end;
end;
x;

FitnessFcn = @(x) traveling_fitness(x,distances);
my_plot = @(options,state,flag) traveling_s_plot(options, ...
    state,flag,locations);
timeBegin = clock;
options = gaoptimset('CreationFcn',@create_permutations, ...
    'CrossoverFcn',@crossover_two_point_order, ...
    'MutationFcn',@ivert_with_landslip, ...
    'PlotFcn', my_plot, ...
    'Generations',500,'PopulationSize',100);

[x,fval,reason,output] = ga(FitnessFcn,cities,options)
timeEnd = clock;
time=timeBegin-timeEnd %знаходження часу роботи оптимізації

 displayEndOfDemoMessage(mfilename)


