anymore() {
echo "$1 ?(y/n) : " 1>&2
read response
case "$response" in

  y|Y) echo 1>&2 ; return 0 ;;
    *) return 1 ;;
esac
} 

