function scores = traveling_fitness(x,distances)
%функція для знаходження довжини шляху
scores = zeros(size(x,1),1);
for j = 1:size(x,1)
    p = x{j}; 
    f = distances(p(end),p(1));
    for i = 2:length(p)
        f = f + distances(p(i-1),p(i));
    end
    scores(j) = f;
end
end
