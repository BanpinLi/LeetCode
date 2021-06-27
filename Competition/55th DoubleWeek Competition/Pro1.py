import copy

nums = [2, 3, 1, 2]
def Pro1(self, nums) -> bool:
    for i in range(len(nums)):
        temp = copy.deepcopy(nums)
        temp.pop(i)
        for j in range(1, len(temp)):
            if temp[j] <= temp[j - 1]:
                break
        else:
            return True
    return False