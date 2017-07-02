def quicksort(l)
  return l if (l.size < 2)

  p = l.shift

  (quicksort(l.select {|n| n < p}) << p << quicksort(l.select {|n| n >= p})).flatten
end

numbers = File.readlines('../source').map(&:to_i)

ordered = quicksort(numbers)

occurrences = ordered.group_by(&:itself).map {|k, v| "#{k}=#{v.size}"}

puts occurrences