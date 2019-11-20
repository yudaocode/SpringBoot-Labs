if redis.call('GET', KEYS[1]) != ARGV[1] then
    return {0}
end
redis.call('SET', KEYS[2], ARGV[2])
return {1}
