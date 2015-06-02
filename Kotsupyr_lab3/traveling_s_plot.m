function state = traveling_s_plot(options,state,flag,locations)
%Функція для виведення графіків
[unused,i] = min(state.Score);
genotype = state.Population{i};

plot(locations(:,1),locations(:,2),'bo');
hold on;
plot(locations(genotype,1),locations(genotype,2));
hold off
end
