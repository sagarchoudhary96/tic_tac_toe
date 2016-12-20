current_position={"top left":" ","top center":" ","top right":" ",
                    "center left":" ","center":" ","center right":" ",
                    "bottom left":" ","bottom center":" ","bottom right":" "}

current_player ='X'
"""
some_dictionary={a_value:"x",some_value:"y"}
some_string="Hello {a_value}, {some_value}".format(**some_dictionary)
print some_string
"""
def display_board(current_positon):
    board ="""
    {top left} | {top center} |{top right}
    ---------
    {center left} | {center} |{center right}
    ---------
    {bottom left} | {bottom center} |{bottom right}
    """.format(**current_positon)
    print board

def user_move(current_position, current_player):
    possible_moves = []
    user_prompt = "please pick your move from the options below!"
    for position in current_position:
        if current_position[position]==" ":
            possible_moves.append(position)
            user_prompt +="\n" + position
    user_prompt+="\n"
    user_choice = raw_input(user_prompt).lower()   # .lower() to make input lowercase
    while user_choice not in possible_moves:
        print "Sorry the position is already taken Enter another position:\n"
        user_choice = raw_input(user_prompt).lower()

    current_position[user_choice] = current_player
    if current_player =='X':
        current_player ='0'
    else:
        current_player ='X'
    return current_position, current_player

def isgame_over(current_position):
    #assuming only one winner
    winners = [["top left","top center","top right"],
               ["center left","center","center right"],
               ["bottom left","bottom center","bottom right"],
               ["top center","center","bottom center"],
               ["top left","center left","bottom left"],
               ["top right","center right","bottom right"],
               ["top left","center","bottom right"],
               ["top right","center","bottom left"]]
    for winning_combo in winners:
        possible_winner = current_position[winning_combo[0]]
        if possible_winner !=" ":
            possibly_won = True
            for value in winning_combo:
                if current_position[value] != possible_winner:
                    possibly_won = False
                    break
            if possibly_won:
                return possible_winner + " Wins!"
    is_draw =False
    for position in current_position:
        if current_position[position]==" ":
            is_draw = False
            return False
    if is_draw:
        return "Draw!"

def play_game():
    current_position = {"top left":" ","top center":" ","top right":" ",
                    "center left":" ","center":" ","center right":" ",
                    "bottom left":" ","bottom center":" ","bottom right":" "}
    current_player ='X'
    result = False
    while  not result:
        display_board(current_position)
        current_position,current_player = user_move(current_position,current_player)
        result = isgame_over(current_position)
        if result:
            display_board(current_position)
            print "GAME OVER"
            print "Result: ",result


play_game()
